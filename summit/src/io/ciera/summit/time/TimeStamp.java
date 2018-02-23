package io.ciera.summit.time;

import io.ciera.summit.application.Application;

@SuppressWarnings("serial")
public class TimeStamp extends java.util.Date {

    public TimeStamp() {
        super( Application.app.getTimeKeeper().currentTimeMicro() / 1000 );
    }

}