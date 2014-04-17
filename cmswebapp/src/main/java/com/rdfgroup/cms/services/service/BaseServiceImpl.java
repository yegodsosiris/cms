package com.rdfgroup.cms.services.service;

import java.util.Comparator;

import com.rdfgroup.cms.services.domain.CmsElement;

public abstract class BaseServiceImpl {


	
	protected Comparator<CmsElement> elementKeyComparator = new Comparator<CmsElement>() {

		public int compare(CmsElement t1, CmsElement t2) {
			return t2.getKey().compareTo(t1.getKey());
		}

	};
	
	protected Comparator<CmsElement> elementVersionComparator = new Comparator<CmsElement>() {
		public int compare(CmsElement t1, CmsElement t2) {
			if (t1.getVersion() > t2.getVersion()) return -1;
			if (t1.getVersion() < t2.getVersion()) return 1;
			return 0;
		}

	};

}
