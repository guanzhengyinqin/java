package internetweather.mode;

import internetweather.observer.Observer;
import internetweather.observer.Subject;

public class Main {
	public static void main(String[] args) {
		Observer mCurrentConditions;
		Observer mForcastConditions;
		Subject mWeatherDataSt;
		
		mWeatherDataSt = new WeatherDataSt();
		mCurrentConditions = new CurrentConditions();
		mForcastConditions = new ForcastConditions();
		
		mWeatherDataSt.registerObserver(mCurrentConditions);
		mWeatherDataSt.registerObserver(mForcastConditions);
		
		//mWeatherDataSt.setData(30, 150, 40);
		
	}
}
