package com.nylas2.delta;

import com.nylas2.AccountOwnedModel;

public interface DeltaStreamListener {

	void onDelta(Delta<? extends AccountOwnedModel> delta);
}
