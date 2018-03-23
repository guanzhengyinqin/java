package internetweather.mode;

import java.util.ArrayList;
import java.util.List;

import internetweather.observer.Observer;
import internetweather.observer.Subject;

public class WeatherDataSt implements Subject {

	private float mTemperatrue;
	private float mPressure;
	private float mHumidity;
	private List<Observer> mObservers;
	
	public WeatherDataSt(){
		mObservers = new ArrayList<Observer>();
	}
	
	
	public void setData(float mTemperatrue,float mPressure,float mHumidity){
		this.mTemperatrue = mTemperatrue;
		this.mPressure = mPressure;
		this.mHumidity = mHumidity;
		dataChange();
	}
	
	@Override
	public void registerObserver(Observer o) {
		// TODO Auto-generated method stub
		mObservers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		// TODO Auto-generated method stub
		if(mObservers.contains(o)){
			mObservers.remove(o);
		}
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(int i=0,len = mObservers.size();i<len;++i){
			mObservers.get(i).update(getTemperatrue(), getPressure(), getHumidity());
		}
	}
	
	public float getTemperatrue(){
		return mTemperatrue;
	}
	public float getPressure(){
		return mPressure;
	}
	public float getHumidity(){
		return mHumidity;
	}
	
	public void dataChange(){
		notifyObservers();
	}

}
