package org.spring.dev.openApi.bus.busStation;

import lombok.Data;

import java.util.List;

@Data
public class MsgBody {
  private List<ItemStationList> itemList;
}
