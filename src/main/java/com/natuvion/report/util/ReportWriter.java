package com.natuvion.report.util;

import java.io.IOException;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.Document;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class ReportWriter {

	/**
	 * Drive Service https://developers.google.com/drive/api/v3/quickstart/java
	 * https://developers.google.com/docs/api/reference/rest/v1/documents/create
	 */
	public void createDoc(Docs service) throws IOException {

		Document doc = new Document().setTitle("My Document");
		doc = service.documents().create(doc).execute();
		System.out.println("Created document with title: " + doc.getTitle());
	}

	/**
	 * The following examples copy an existing document. You can find the ID to use
	 * for the Drive API call in the URL of the document. rest api call
	 * https://docs.google.com/document/d/document_id/edit
	 * 
	 * @throws IOException
	 */
	public void copyingExistingDocument(

			String title, Drive driveService, String documentId) throws IOException {

		String copyTitle = title;
		File copyMetadata = new File().setName(copyTitle);
		File documentCopyFile = driveService.files().copy(documentId, copyMetadata).execute();
		String documentCopyId = documentCopyFile.getId();
	}

	private String copySheetFromDrive(Drive driveService, String olfFilename, String newFilename) throws IOException {

		File newSheet = new File();
		newSheet.setName(olfFilename);
		Drive.Files.Copy copyFile = driveService.files().copy(newFilename, newSheet);
		updateProperty(copyFile.getFileId());
		System.out.println("File ID: " + copyFile.getFileId());
		return copyFile.getFileId();
	}

	private void updateProperty(String fileId) {
		// TODO Auto-generated method stub

	}
}