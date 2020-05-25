package com.natuvion.report.layout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.DeleteContentRangeRequest;
import com.google.api.services.docs.v1.model.Dimension;
import com.google.api.services.docs.v1.model.InsertInlineImageRequest;
import com.google.api.services.docs.v1.model.InsertTextRequest;
import com.google.api.services.docs.v1.model.Location;
import com.google.api.services.docs.v1.model.Range;
import com.google.api.services.docs.v1.model.ReplaceAllTextRequest;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.Size;
import com.google.api.services.docs.v1.model.SubstringMatchCriteria;

public class LayoutElements {
	
	/**
	 * https://developers.google.com/docs/api/concepts/rules-behavior#deleting_text
	 */

	List<Request> requests = new ArrayList<>();
	
	public void replaceVar(String DOCUMENT_ID, String var, String replacement, Docs service) throws IOException {
		
		//String varFormat = String.format("{{%s}}", var);
		
        requests.add(new Request()
                .setReplaceAllText(new ReplaceAllTextRequest()
                        .setContainsText(new SubstringMatchCriteria()
                                .setText(String.format("{{%s}}", var))
                                .setMatchCase(true))
                        .setReplaceText(replacement)));
		
        update(service, DOCUMENT_ID);
	}
	
	private void insertingText(
			
			String text, int idx,
			String DOCUMENT_ID, Docs docsService
			) throws IOException {
		
        requests.add(new Request().setInsertText(new InsertTextRequest()
                .setText(text)
                .setLocation(new Location().setIndex(idx))));

        update(docsService, DOCUMENT_ID);
	}
	
	
	private void deleteContent(Docs docsService, String DOCUMENT_ID) throws IOException {
		
        requests.add(new Request().setDeleteContentRange(
                new DeleteContentRangeRequest()
                        .setRange(new Range()
                                .setStartIndex(10)
                                .setEndIndex(24))
            ));

        update(docsService, DOCUMENT_ID);
	}
	
	public void addImage(String url, Docs docsService, String DOCUMENT_ID) throws IOException {
		
		/**
		 * url example: "https://www.gstatic.com/images/branding/product/1x/docs_64dp.png"
		 */
		
		//docsService.documents().get(DOCUMENT_ID).get
		
	    requests.add(new Request().setInsertInlineImage(new InsertInlineImageRequest()
	            .setUri(url)
	            .setLocation(new Location().setIndex(1))
	            .setObjectSize(new Size()
	                    .setHeight(new Dimension()
	                            .setMagnitude(50.0)
	                            .setUnit("PT"))
	                    .setWidth(new Dimension()
	                            .setMagnitude(50.0)
	                            .setUnit("PT")))));

	    update(docsService, DOCUMENT_ID);
	}
	
	//https://developers.google.com/chart/interactive/docs/gallery/piechart
	
	private void update(Docs docsService, String DOCUMENT_ID) throws IOException {

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		//BatchUpdateDocumentResponse response = docsService.documents().batchUpdate(DOCUMENT_ID, body).execute();
		docsService.documents().batchUpdate(DOCUMENT_ID, body).execute();
	}
}