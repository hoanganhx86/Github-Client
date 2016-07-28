package com.anhnguyen.githubclient;

import android.util.Log;

import java.util.Locale;

public class RLog {
	public static void d(String tag, String msg){
		if(Config.DEBUG){
			Log.d(tag, msg);
		}
	}

	public static void d(String tag, String msg, Object...params){
		if(Config.DEBUG){
			Log.d(tag, String.format(Locale.US, msg, params));
		}
	}
	
	public static void e(String tag, String msg){
		if(Config.DEBUG){
			Log.e(tag, msg);
		}
	}

    public static void e(String tag, String msg, Object...params){
        if(Config.DEBUG){
            Log.e(tag, String.format(Locale.US, msg, params));
        }
    }
	
	public static void i(String tag, String msg){
		if(Config.DEBUG){
			Log.i(tag, msg);
		}
	}

    public static void i(String tag, String msg, Object...params){
        if(Config.DEBUG){
            Log.i(tag, String.format(Locale.US, msg, params));
        }
    }
	
	public static void v(String tag, String msg){
		if(Config.DEBUG){
			Log.v(tag, msg);
		}
	}

    public static void v(String tag, String msg, Object...params){
        if(Config.DEBUG){
            Log.v(tag, String.format(Locale.US, msg, params));
        }
    }
	
	public static void w(String tag, String msg){
		if(Config.DEBUG){
			Log.w(tag, msg);
		}
	}

    public static void w(String tag, String msg, Object...params){
        if(Config.DEBUG){
            Log.w(tag, String.format(Locale.US, msg, params));
        }
    }
	
	public static void d(String tag, String msg, Throwable tr){
		if(Config.DEBUG){
			Log.d(tag, msg, tr);
		}
	}
	
	public static void e(String tag, String msg, Throwable tr){
		if(Config.DEBUG){
			Log.e(tag, msg, tr);
		}
	}
	
	public static void i(String tag, String msg, Throwable tr){
		if(Config.DEBUG){
			Log.i(tag, msg, tr);
		}
	}
	
	public static void v(String tag, String msg, Throwable tr){
		if(Config.DEBUG){
			Log.v(tag, msg, tr);
		}
	}
	
	public static void w(String tag, String msg, Throwable tr){
		if(Config.DEBUG){
			Log.w(tag, msg, tr);
		}
	}
	
	public static void w(String tag, Throwable tr){
		if(Config.DEBUG){
			Log.w(tag, tr);
		}
	}
	
	
	
}
