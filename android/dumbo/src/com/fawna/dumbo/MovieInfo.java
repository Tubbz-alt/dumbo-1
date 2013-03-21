package com.fawna.dumbo;

import org.json.JSONObject;
import org.json.JSONArray;
import java.util.ArrayList;

public class MovieInfo 
{
  public String imdb, name;
  public long time;
  public ArrayList<MovieEvent> events;
  
  public MovieInfo(JSONObject resp) {
    try {
      JSONObject offset = resp.getJSONObject("offset");
      time = (int)((offset.getDouble("time") + 30));

      JSONObject metadata = resp.getJSONObject("metadata");
      name = metadata.getString("name");
      imdb = metadata.getString("imdb_url");

      //TODO parse the roles

      JSONArray ray = resp.getJSONArray("events");

      events = new ArrayList<MovieEvent>();

      for (int i = 0; i < ray.length(); i++) {
        JSONObject ev = ray.getJSONObject(i);

        MovieEvent newMovEv = new MovieEvent(ev);
        
        events.add(newMovEv);
      }
    }
    catch (Exception e) { 
      throw new RuntimeException(e);
    }
  }

}