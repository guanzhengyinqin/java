package iteratordemo;

/**
 * 自定义迭代器接口	类似于java.util.Iterator
 * 用于遍历集合类ICollection的数据
 * @author guanzheng
 *
 * @param <T>
 */

public interface IIterator<T> {

	boolean hasNext();
	boolean hasPrevious();
	T next();
	T previous();
	
}
