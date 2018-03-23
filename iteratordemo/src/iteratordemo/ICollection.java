package iteratordemo;

/**
 * 自定义集合数据接口,类似java.util.Collection
 * 用于存储数据
 * @author guanzheng
 *
 * @param <T>
 */

public interface ICollection<T> {
	
	IIterator<T> iterator();	//返回迭代器
	void add(T t);
	T get(int index);

}
