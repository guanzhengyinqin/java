package internetweather;

public class WeatherData {

	private float mTemperature;
	private float mPressure;
	private float mHumidity;
	private CurrentConditions mCurrentConditions;
	
	public WeatherData(CurrentConditions mCurrentConditions){
		this.mCurrentConditions = mCurrentConditions;
	}
	
	public void update(float mTemperature,float mPressure,float mHumidity){
		this.mTemperature = mTemperature;
		this.mPressure = mPressure;
		this.mHumidity = mHumidity;
		display();
	}
	
	public void display(){
		System.out.println("**Today mTemperature:"+mTemperature);
		System.out.println("**Today mTemperature:"+mPressure);
		System.out.println("**Today mTemperature:"+mHumidity);
	}
	
	
	
	public float getTemperature() {
		return mTemperature;
	}

	public float getPressure() {
		return mPressure;
	}

	public float getHumidity() {
		return mHumidity;
	}

	public void dataChange(){
		mCurrentConditions.update(getTemperature(), getPressure(), getHumidity());
	}
	
	public void setData(float mTemperature,float mPressure,float mHumidity){
		this.mTemperature = mTemperature;
		this.mPressure = mPressure;
		this.mHumidity = mHumidity;
		dataChange();
	}
	
}
