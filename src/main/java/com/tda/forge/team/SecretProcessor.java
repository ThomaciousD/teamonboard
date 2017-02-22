package com.tda.forge.team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class SecretProcessor implements Processor {
	private Logger logger = LoggerFactory.getLogger(SecretProcessor.class);

	private Properties properties;

	private boolean deploy;

	public void process(Exchange exchange) throws Exception {
		String body = exchange.getIn().getBody(String.class);
		String project = exchange.getProperty("projectId", String.class);
		if (!isDeploy()) {
			BufferedWriter fos = null;
			// store the fe
			try {
				fos = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("config.json"))));
				logger.info("File {} created.", fos);
				fos.write(body);
				fos.flush();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				if (fos != null) {
					fos.close();
				}
			}
		} else {
			doCreateSecret(project, "jenkins-docker-cfg", body);
		}
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean isDeploy() {
		return deploy;
	}

	public void setDeploy(boolean deploy) {
		this.deploy = deploy;
	}

	protected void doCreateSecret(String namespace, String name, String source) {
		Config config = new ConfigBuilder().withMasterUrl("https://kubernetes").build();
		KubernetesClient kubernetesClient = new DefaultKubernetesClient(config);
		Secret sec = createEmptySecret(name);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("config.json", new String(Base64.getEncoder().encode(source.getBytes())));
		sec.setData(map);
		logger.info("Creating a Secret from " + source + " namespace " + namespace + " name " + name);
		try {
			kubernetesClient.secrets().inNamespace(namespace).create(sec);
			logger.info("Secret created in namespace {} ", namespace);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create Secret from " + source + ". " + e + ". ", e);
		} finally {
			kubernetesClient.close();
		}
	}

	public static Secret createEmptySecret(String name) {
		Secret secret = new Secret();
		ObjectMeta objectMeta = new ObjectMeta();
		objectMeta.setName(name);
		secret.setMetadata(objectMeta);
		return secret;
	}
}