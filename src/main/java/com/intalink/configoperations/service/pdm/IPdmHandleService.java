package com.intalink.configoperations.service.pdm;

import java.io.InputStream;

public interface IPdmHandleService {
    int autoSaveFromPdm(InputStream pdmStream, String modelId) throws Exception;
}

