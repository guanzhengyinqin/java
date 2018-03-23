package annotationdemo;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitColor {
	
	String name() default "";
	
	/**
	 * 颜色枚举
	 * @author user
	 *
	 */
	public enum Color {BULE,RED,GREEN};
	
	/**
	 * 颜色属性
	 * @return
	 */
	Color fruitColor() default Color.GREEN;

}
