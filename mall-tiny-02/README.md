# Swagger-UI #
Swagger-UI是HTML, Javascript, CSS的一个集合，可以动态地根据注解生成在线API文档。
## 常用注解 ##
* @Api：用于修饰Controller类，生成Controller相关文档信息
* @ApiOperation：用于修饰Controller类中的方法，生成接口方法相关文档信息
* @ApiParam：用于修饰接口中的参数，生成接口参数相关文档信息
* @ApiModelProperty：用于修饰实体类的属性，当实体类是请求参数或返回结果时，直接生成相关文档信息