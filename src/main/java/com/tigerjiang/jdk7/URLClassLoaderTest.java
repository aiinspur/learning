package com.tigerjiang.jdk7;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Java应用，特别是大型的Java应用，往往都需要动态的加载类或Jar，URLClassLoader提供了这个功能，它让我们可以通过以下几种方式进行加载：
 * 文件: (从文件系统目录加载) jar包: (从Jar包进行加载) Http: (从远程的Http服务进行加载)
 * 常见的问题是，当class文件或者resources资源文件更新后，我们需要重新加载这些类或者Jar。从理论上来说，当应用清理了对所加载的对象的引用，
 * 那么垃圾收集器就会将这些对象给收集掉，然后我们再重新加载新的JAR文件，并创建一个新的URLClassLoader来加载。可是这里有一个问题，
 * 就是我们不知道垃圾收集器什么时候将那些未被引用的对象给收集掉，特别是在Windows中，因为在Windows中打开的文件是不可以被删除或被替换的。
 * 在Java7的Build48版中，URLClassLoader提供了close()这个方法，可以将打开的资源全部释放掉，这个给开发者节省了大量的时间来精力来处理这方面的问题。
 * 
 * @author tiger
 *
 */

public class URLClassLoaderTest {

	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("file:TestJdk7.class");
			URLClassLoader loader = new URLClassLoader(new URL[] { url });
			Class<?> cl = Class.forName("com.tigerjiang.jdk7.TestJdk7", true,
					loader);
			System.out.println(cl.isInterface());
			Runnable foo = (Runnable) cl.newInstance();
			foo.run();
			;
			loader.close();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | IOException
				| IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}

	}

}
