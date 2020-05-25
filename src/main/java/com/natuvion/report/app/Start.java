package com.natuvion.report.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.docs.v1.Docs;
import com.natuvion.report.service.DocsQuickstart;
import com.natuvion.report.util.ReportWriter;

public class Start {

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		
		
		/**
		 * since I will not share my private client information on a public github repo
		 * you have to follow the tutorial on your own
		 *
		 * https://developers.google.com/drive/api/v3/quickstart/java
		 * set up the client and generate api key
		 * client id ...
		 * client secret ...
		 * api key ...
		 */
		
		DocsQuickstart client = new DocsQuickstart();
		Docs service = client.startService();

		ReportWriter writer = new ReportWriter();
		writer.createDoc(service);

	}

}