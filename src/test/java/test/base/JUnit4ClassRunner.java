package test.base;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 初始化log4j配置
 *
 * <p>
 * 初始化log4j配置
 * 
 * @author yangchengbiao 2016年10月8日
 * @see [相关类/方法]
 * @since 1.0
 */
public class JUnit4ClassRunner extends SpringJUnit4ClassRunner
{ 
    public JUnit4ClassRunner(Class<?> clazz) throws InitializationError
    {
        super(clazz);
    }
}
