package org.spring.dev.openApi.bus;

import lombok.Data;

import java.util.List;

@Data
public class MsgBody{

  private List<ItemList> itemList;
}
