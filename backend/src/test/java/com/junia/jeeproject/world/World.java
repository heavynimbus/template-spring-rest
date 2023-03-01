package com.junia.jeeproject.world;

import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class World extends HashMap<WorldKey, Object> {

  public <T> T get(WorldKey key) {
    return (T) super.get(key);
  }
}
