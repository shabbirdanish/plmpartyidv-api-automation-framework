package com.santander.api.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;

@Component
public class JsonValueReader {
    @Autowired
    private JsonValueReader jsonValueReader;
    public JsonReader getJsonReader(String path){
        InputStream is=    getClass().getClassLoader().getResourceAsStream(path);
   //      getClass().getClassLoader().getResourceAsStream("testdata/intiate/Initiate/partyIdv_initiateTemplate.json");
        JsonReader reader = Json.createReader(is);
        return reader;
    }

    public String getPartyID(String jsonFilePath){
        JsonReader reader= jsonValueReader.getJsonReader(jsonFilePath);
        JsonObject obj = reader.readObject();
        JsonObject idvSeesionObj= obj.getJsonObject("idvSession");
        JsonObject partyObj = idvSeesionObj .getJsonObject("party");
        String partyId = partyObj.getString("partyId");
        return partyId;
    }
}
