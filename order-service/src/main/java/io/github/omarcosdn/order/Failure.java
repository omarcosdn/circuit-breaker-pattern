package io.github.omarcosdn.order;

import lombok.Data;

@Data
public class Failure implements Type {

  private final String msg;
}
