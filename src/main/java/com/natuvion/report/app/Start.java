package com.natuvion.report.app;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.drive.Drive;
import com.natuvion.report.layout.LayoutElements;
import com.natuvion.report.service.DocsQuickstart;
import com.natuvion.report.service.DriveQuickstart;
import com.natuvion.report.util.ReportWriter;

public class Start {

	public static void main(String[] args) throws IOException, GeneralSecurityException {
		
		/**
		 * since I will not share my private client or api information on a public github repo
		 * you have to follow the tutorial on your own.
		 * Download and pass the credentials.json in .\src\main\resources
		 * You have to authorize and generate a token with a google account
		 * Reset the tokens e.g stored credentials, if you changed the authentication scope!
		 *
		 * https://developers.google.com/drive/api/v3/quickstart/java
		 * client id ...
		 * client secret ...
		 */
		
		
		/**
		 * 
		 * TODO kopie vom basis template erstellen
		 * replacements
		 * update
		 * 
		 * TODO
		 * tabellen dynamisch befüllen
		 * 
		 * TODO
		 * wie funktionieren charts, Datenanbindung
		 * 
		 */
		
		DocsQuickstart docClientService 		= new DocsQuickstart();
		DriveQuickstart driveClientService 		= new DriveQuickstart();
		ReportWriter writer 					= new ReportWriter();
		
		Docs docService 	= docClientService.startService();
		Drive driveService 	= driveClientService.startService();
		
		driveClientService.listFiles(driveService);

		//writer.createDoc(docService);
		
		//writer.copyingExistingDocument("Basic Template - Kopie", driveService, "1vI3Cf85EnqR0yl0gZ3glBgV3jk4_jvJoEeOqPuwjadw");
		
		//driveClientService.listFiles(driveService);
		
		//1NxhgKMINkenHP6gMat5Pe3146UA5YRDoYkxHZM7vmYs
		
		LayoutElements layout = new LayoutElements();
		layout.replaceVar("1NxhgKMINkenHP6gMat5Pe3146UA5YRDoYkxHZM7vmYs", "nat.projectname", "NAT DUMMY PROJECT", docService);
		layout.addImage("https://www.gstatic.com/images/branding/product/1x/docs_64dp.png", docService, "1NxhgKMINkenHP6gMat5Pe3146UA5YRDoYkxHZM7vmYs");
	}
}