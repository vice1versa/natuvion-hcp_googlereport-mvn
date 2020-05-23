package com.natuvion.report.layout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.docs.v1.Docs;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest;
import com.google.api.services.docs.v1.model.BatchUpdateDocumentResponse;
import com.google.api.services.docs.v1.model.Color;
import com.google.api.services.docs.v1.model.Dimension;
import com.google.api.services.docs.v1.model.Link;
import com.google.api.services.docs.v1.model.OptionalColor;
import com.google.api.services.docs.v1.model.Range;
import com.google.api.services.docs.v1.model.Request;
import com.google.api.services.docs.v1.model.RgbColor;
import com.google.api.services.docs.v1.model.TextStyle;
import com.google.api.services.docs.v1.model.UpdateTextStyleRequest;
import com.google.api.services.docs.v1.model.WeightedFontFamily;

public class CharacterFormatting {

	/**
	 * Changing character formatting
	 * https://developers.google.com/docs/api/how-tos/format-text
	 * https://developers.google.com/docs/api/concepts/structure
	 */

	List<Request> requests = new ArrayList<>();

	private void format(
			String style, 
			int start, int end, 
			Docs docsService, String DOCUMENT_ID
			) throws IOException {

		/**
		 * style example: "bold"
		 */

		requests.add(new Request().setUpdateTextStyle(new UpdateTextStyleRequest()

				.setTextStyle(new TextStyle().setBold(true).setItalic(true))
				.setRange(new Range().setStartIndex(start).setEndIndex(end)).setFields(style)));

		update(docsService, DOCUMENT_ID);
	}

	private void format(
			String font, 
			int start, int end, 
			RgbColor color, 
			Docs docsService, String DOCUMENT_ID
			) throws IOException {

		/**
		 * font example: "Times New Roman" RgbColor example: new
		 * RgbColor().setBlue(1.0F).setGreen(0.0F).setRed(0.0F)
		 */

		requests.add(new Request().setUpdateTextStyle(new UpdateTextStyleRequest()
				.setRange(new Range().setStartIndex(start).setEndIndex(end))
				.setTextStyle(new TextStyle().setWeightedFontFamily(new WeightedFontFamily().setFontFamily(font))
						.setFontSize(new Dimension().setMagnitude(14.0).setUnit("PT"))
						.setForegroundColor(new OptionalColor().setColor(new Color().setRgbColor(color))))
				.setFields("foregroundColor,weightedFontFamily,fontSize")));

		update(docsService, DOCUMENT_ID);
	}

	private void format(
			String url, 
			Docs docsService, String DOCUMENT_ID
			) throws IOException {

		requests.add(new Request()
				.setUpdateTextStyle(new UpdateTextStyleRequest().setRange(new Range().setStartIndex(11).setEndIndex(15))
						.setTextStyle(new TextStyle().setLink(new Link().setUrl(url))).setFields("link")));

		update(docsService, DOCUMENT_ID);
	}

	private void update(Docs docsService, String DOCUMENT_ID) throws IOException {

		BatchUpdateDocumentRequest body = new BatchUpdateDocumentRequest().setRequests(requests);
		BatchUpdateDocumentResponse response = docsService.documents().batchUpdate(DOCUMENT_ID, body).execute();
	}
}