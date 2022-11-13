package com.santander.api.automation.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonReader;
import java.io.InputStream;


@Service
public class JsonValueReader {

    public JsonReader getJsonReader(String path){
        InputStream is=    getClass().getClassLoader().getResourceAsStream(path);
        JsonReader reader = Json.createReader(is);
        return reader;
    }

//    public String getPartyID(String jsonFilePath){
//        JsonReader reader= jsonValueReader.getJsonReader(jsonFilePath);
//        JsonObject obj = reader.readObject();
//        JsonObject idvSeesionObj= obj.getJsonObject("idvSession");
//        JsonObject partyObj = idvSeesionObj .getJsonObject("party");
//        String partyId = partyObj.getString("partyId");
//        return partyId;
//    }
}
