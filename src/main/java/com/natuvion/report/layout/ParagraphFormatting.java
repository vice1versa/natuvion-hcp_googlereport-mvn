package com.natuvion.report.layout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.Color;
import com.google.api.services.docs.v1.model.Dimension;
import com.google.api.services.docs.v1.model.OptionalColor;
import com.google.api.services.docs.v1.model.ParagraphBorder;
import com.google.api.services.docs.v1.model.ParagraphStyle;
import com.google.api.services.docs.v1.model.Range;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.RgbColor;
import com.google.api.services.docs.v1.model.UpdateParagraphStyleRequest;

public class ParagraphFormatting {

	List<Request> requests = new ArrayList<>();

	private void format(Docs docsService, String DOCUMENT_ID) throws IOException {

		requests.add(new Request().setUpdateParagraphStyle(
				new UpdateParagraphStyleRequest().setRange(new Range().setStartIndex(1).setEndIndex(10))
						.setParagraphStyle(new ParagraphStyle().setNamedStyleType("HEADING_1")
								.setSpaceAbove(new Dimension().setMagnitude(10.0).setUnit("PT"))
								.setSpaceBelow(new Dimension().setMagnitude(10.0).setUnit("PT")))
						.setFields("namedStyleType,spaceAbove,spaceBelow")));
		
		update(docsService, DOCUMENT_ID);
	}

	private void formatWithColor(Docs docsService, String DOCUMENT_ID, RgbColor color) throws IOException {

		/**
		 * Color example: new RgbColor().setBlue(1.0F).setGreen(0.0F).setRed(0.0F)
		 */
		
		requests.add(new Request().setUpdateParagraphStyle(new UpdateParagraphStyleRequest()
				.setRange(new Range().setStartIndex(10).setEndIndex(20))
				.setParagraphStyle(new ParagraphStyle().setBorderLeft(new ParagraphBorder()
						.setColor(new OptionalColor().setColor(
								new Color().setRgbColor(color)))
						.setDashStyle("DASH").setPadding(new Dimension().setMagnitude(20.0).setUnit("PT"))
						.setWidth(new Dimension().setMagnitude(15.0).setUnit("PT"))))
				.setFields("borderLeft")));

		update(docsService, DOCUMENT_ID);
	}

	private void update(Docs docsService, String DOCUMENT_ID) throws IOException {

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = docsService.documents().batchUpdate(DOCUMENT_ID, body).execute();
	}
}