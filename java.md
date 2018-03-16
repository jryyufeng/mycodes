>**java面试总结1**
> - **http://blog.csdn.net/u010601183/article/details/56496424**
> - **http://blog.csdn.net/v_JULY_v/article/details/6105630**
---------

## java标识符
* 1.长度：可以任意长度，但是JVM虚拟机规范中长度要求<=65535，这个长度仅限于空字符null以外的字符
* 2.合法性：
    * （1）、以_、$、字母开头，后面可以是以上三种字符或者数字，此处字母不再单指26个英文字母的大小写，而是指Unicode字符集
    * （2）、首字母所对应的代码点必须使Character类的isJavaIdentifierStar方法返回true
    * （3）、不能与常量、保留字、修饰符等相同 补：java保留字goto、const无实际意义，修饰符、常量后续详解
* 3.$字符可以使用，但应该减少使用，$还作为编译器中生成的标识符名称使用
    * 例：假设存在类A、A中变量B则编译后会生成A$B.class
    *     若存在类A$B、类A、A中变量B则编译后生成A$B.class A.class A$B.class 会报错误



---------
