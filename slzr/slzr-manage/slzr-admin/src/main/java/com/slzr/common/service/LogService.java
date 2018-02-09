package com.slzr.common.service;

import com.slzr.common.common.utils.*;
import org.springframework.stereotype.Service;

import com.slzr.common.domain.LogDO;
import com.slzr.common.domain.PageDO;

@Service
public interface LogService {
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
