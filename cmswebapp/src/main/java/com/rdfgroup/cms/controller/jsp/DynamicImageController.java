package com.rdfgroup.cms.controller.jsp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rdfgroup.cms.services.repository.AssetRepository;

/**
 * 
 * Dynamicaly reads from MongoDB and converts to an image.
 * 
 * @author dave.hampton
 *
 */
@Controller
@RequestMapping("images")
public class DynamicImageController {
	
	@Autowired
	private AssetRepository assetRepository;
	
	private static final Map<String, MediaType> mediaTypes = new HashMap<String, MediaType>();
	
	static {
		mediaTypes.put("gif", MediaType.IMAGE_GIF);
		mediaTypes.put("jpg", MediaType.IMAGE_JPEG);
		mediaTypes.put("png", MediaType.IMAGE_PNG);
	}

	@RequestMapping("/{name}.{ext}")
	public ResponseEntity<byte[]> getImage(@PathVariable String name, @PathVariable String ext) throws IOException {
	    final HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(mediaTypes.get(ext));
	    return new ResponseEntity<byte[]>(IOUtils.toByteArray(assetRepository.getImage(name+"."+ext).getInputStream()), headers, HttpStatus.OK);
	}

}
