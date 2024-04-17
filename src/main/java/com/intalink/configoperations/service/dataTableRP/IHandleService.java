package com.intalink.configoperations.service.dataTableRP;

import java.io.InputStream;

public interface IHandleService {
    int tableRpFromPdm(InputStream pdmStream, String modelId) throws Exception;
    int tableRpFromLdm(InputStream ldmStream, String modelId) throws Exception;
}

