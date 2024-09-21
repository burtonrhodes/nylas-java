package com.nylas2.delta;

public interface DeltaLongPollListener {

	void onDeltaCursor(DeltaCursor deltaCursor);
}
