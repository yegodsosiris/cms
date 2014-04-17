package com.rdfgroup.cms.controller.jsp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.rdfgroup.cms.model.AssetUploadModel;
import com.rdfgroup.cms.services.domain.Asset;
import com.rdfgroup.cms.services.domain.Website;
import com.rdfgroup.cms.services.service.AssetService;

@Controller
@RequestMapping("/asset")
public class AssetJspController extends BaseJspController implements HandlerExceptionResolver {

	private static final String ASSET_REVERTLIST = "asset/revertlist";
	private static final String ASSET_REVERT = "asset/revert";
	private static final String UPLOAD_MODEL = "model";
	private static final String ASSET_MODEL = "asset";
	private static final String MODEL_ASSETS = "assets";
	private static final String ASSET_LIST = "asset/list";
	private static final String EDIT_ASSET = "asset/edit";
	private static final String ASSET = "asset/asset";

	@Autowired
	private AssetService assetService;

	/* =================================================================== */

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_NEW+"/{type}", method = RequestMethod.GET)
	public String showForm(ModelMap model, @PathVariable String type) {
		AssetUploadModel assetUploadModel = new AssetUploadModel(type);
		model.addAttribute(UPLOAD_MODEL, assetUploadModel);
		return EDIT_ASSET;
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_NEW+"/{type}", method = RequestMethod.POST)
	public String processForm(ModelMap model, HttpServletRequest request, @ModelAttribute(value = UPLOAD_MODEL) AssetUploadModel assetUploadModel, BindingResult result) throws Exception {

		Asset savedFile = assetService.saveAsset(assetUploadModel, (Website) model.get(Website.KEY));
		model.addAttribute(UrlMappings.MESSAGE, "Asset successfully saved. Now at version " + (savedFile.getVersion()+1));
		return EDIT_ASSET;
	}

	/* =================================================================== */

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_EDIT, method = RequestMethod.GET)
	public String getAssetForEdit(ModelMap model, @PathVariable String id) throws Exception {
		Asset asset = assetService.getAsset(id);
		AssetUploadModel assetUploadModel = new AssetUploadModel(asset);
		model.addAttribute(UPLOAD_MODEL, assetUploadModel);
		model.addAttribute("editmode", true);
		return EDIT_ASSET;
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_EDIT, method = RequestMethod.POST)
	public String updateAsset(ModelMap model, HttpServletRequest request, @ModelAttribute(value = UPLOAD_MODEL) AssetUploadModel assetUploadModel, BindingResult result) throws Exception {
		Asset asset = assetService.updateAsset(request, assetUploadModel);
		model.addAttribute(UrlMappings.MESSAGE, "Content successfully saved. Now at version " + (asset.getVersion()+1));
		return EDIT_ASSET;
	}

	/* =================================================================== */

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_DEFAULT, method = RequestMethod.GET)
	public String showImages(ModelMap model, @PathVariable String type) {
		List<Asset> assets = assetService.getAssetsByType(type, getWebsite(model));
		model.addAttribute(MODEL_ASSETS, assets);
		return ASSET_LIST;
	}


	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_ALL, method = RequestMethod.GET)
	public String getGlobalContentsByType(ModelMap model, @PathVariable String type) throws Exception {
		List<Asset> assets = assetService.getAssetsByType(type, null);
		model.addAttribute(MODEL_ASSETS, assets);
		model.addAttribute("type", type);
		return ASSET_LIST;
	}

	/* =================================================================== */
	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_REVERT, method = RequestMethod.GET)
	public String getContentToRevert(ModelMap model, @PathVariable String id) throws Exception {
		AssetUploadModel assetUploadModel = new AssetUploadModel(assetService.getAsset(id));
		model.addAttribute(UPLOAD_MODEL, assetUploadModel);
		return ASSET_REVERT;
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_REVERT, method = RequestMethod.POST)
	public String revertContent(ModelMap model, @ModelAttribute(value = UPLOAD_MODEL) AssetUploadModel assetUploadModel, BindingResult result) {
		Asset asset = assetService.revertAsset(assetUploadModel.getAsset(), getWebsite(model));
		model.addAttribute(UrlMappings.MESSAGE, "Content successfully reverted. Now at version " + asset.getVersion());
		return ASSET_REVERT;
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_VERSIONS, method = RequestMethod.GET)
	public String getContentsByTypeToRevert(ModelMap model, @PathVariable String key) throws Exception {
		return getRevertList(model, key, getWebsite(model));
	}

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_ALL_VERSIONS, method = RequestMethod.GET)
	public String getGlobalContentsByTypeToRevert(ModelMap model, @PathVariable String key) throws Exception {
		return getRevertList(model, key, null);
	}

	/* =================================================================== */

	private String getRevertList(ModelMap model, String key, Website website) {
		List<Asset> assets = assetService.getAssetsByKey(key, website);
		model.addAttribute("assets", assets);
		model.addAttribute("key", key);
		if (assets != null && !assets.isEmpty()) {
			model.addAttribute("type", assets.get(0).getType());
		}
		return ASSET_REVERTLIST;
	}

	/* =================================================================== */

	@RequestMapping(value = UrlMappings.REQUEST_MAPPING_DELETE, method = RequestMethod.GET)
	public String deleteContent(ModelMap model, @PathVariable String id)	throws Exception {
		Asset asset = assetService.getAsset(id);
		String p = asset.getType();
		assetService.deleteAsset(id);
		return "forward:/asset/"+p;
	}

	/* =================================================================== */

	@Override
	public ModelAndView resolveException(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception exception) {
		Map<Object, Object> model = new HashMap<Object, Object>();
		if (exception instanceof MaxUploadSizeExceededException) {
			model.put("errors", "File size should be less then " + ((MaxUploadSizeExceededException) exception).getMaxUploadSize() + " byte.");
		} else {
			model.put("errors", "Unexpected error: " + exception.getMessage());
		}
		model.put(UPLOAD_MODEL, new AssetUploadModel());
		return new ModelAndView("/image", (Map) model);

	}
}
