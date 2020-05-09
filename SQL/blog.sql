/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `blog_tags` */

DROP TABLE IF EXISTS `blog_tags`;

CREATE TABLE `blog_tags` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `bid` int(100) DEFAULT NULL,
  `tagid` int(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `blog_tags` */

insert  into `blog_tags`(`id`,`bid`,`tagid`) values (11,17,8),(12,17,9),(13,17,10),(14,18,8),(15,18,10),(16,19,8),(17,19,10),(18,20,8),(19,21,9),(20,22,9),(21,22,10),(22,22,8),(23,23,8),(24,23,10),(25,24,8),(26,24,9),(27,25,8),(28,25,10),(29,25,9),(30,25,12),(31,26,13),(32,26,12),(33,26,10),(34,26,11),(35,26,14),(36,27,12),(37,27,14),(38,28,9),(39,28,10),(40,29,13),(41,29,11),(42,29,17),(43,30,8),(44,30,10),(45,30,15),(46,31,13),(47,31,11),(48,31,10),(49,32,9),(50,32,11),(51,32,13),(52,32,9),(53,32,10),(54,32,11);

/*Table structure for table `blogs` */

DROP TABLE IF EXISTS `blogs`;

CREATE TABLE `blogs` (
  `bid` int(100) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `content` longtext,
  `firstpicture` varchar(200) DEFAULT NULL,
  `flag` varchar(10) DEFAULT NULL,
  `views` int(220) DEFAULT NULL,
  `appreciation` tinyint(1) DEFAULT '1',
  `sharestatement` tinyint(1) DEFAULT '1',
  `commentabled` tinyint(1) DEFAULT '1',
  `published` tinyint(1) DEFAULT '1',
  `recommend` tinyint(1) DEFAULT '1',
  `cratetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `typeid` int(50) DEFAULT NULL,
  `uid` int(50) DEFAULT NULL,
  `describes` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `blogs` */

insert  into `blogs`(`bid`,`title`,`content`,`firstpicture`,`flag`,`views`,`appreciation`,`sharestatement`,`commentabled`,`published`,`recommend`,`cratetime`,`typeid`,`uid`,`describes`) values (17,'Mybatis纯注解遇到的问题','## 第一部分\r\n刚完成新增博客功能，迫不及待来写一篇，记录一下今天遇到**BUG**:tw-1f31a:的解决方法\r\n### 背景：\r\n我要完成的是一个博客可以有多个标签，完成添加功能时就需要从前端获取到类型和标签，从前端获取的标签我使用字符串接收的\r\n因为前端传过来是这样（1,2,3..）如果想在后台使用把他们当做条件必须转换为集合，或者Map，我这里是在controller用String接收然后在service分割转换迭代存到List中，上代码：\r\nController：\r\n```java\r\nblog.setTagList(tagsService.getByIdsTags(blog.getTagids()));\r\n```\r\nServiceImp：\r\n\r\n```java\r\n\r\n /**通过集合id查询*/\r\n    public List<Tag> getByIdsTags(String tagids) {\r\n        return tagMapper.getByIdsTags(coverToList(tagids));\r\n    }\r\n/*把得到的一串id截取存为数组*/\r\n    private List<Integer> coverToList(String tagids) {\r\n        List<Integer> list=new ArrayList<>();\r\n        if(!\"\".equals(tagids)&&tagids!=null){\r\n            String[] idarray=tagids.split(\",\");\r\n            for(int i=0;i<idarray.length;i++){\r\n                list.add(new Integer(idarray[i]));\r\n            }\r\n        }\r\n        return list;\r\n    }\r\n```\r\n#### Mapper：我用的是纯注解没有xml\r\n```java\r\n /**通过集合id查询*/\r\n    @Select(\"<script>\"\r\n               + \"SELECT * FROM tag WHERE tagid IN \"\r\n               + \"<foreach collection=\'tagids\' item=\'item\' index=\'index\' open=\'(\' separator=\',\' close=\')\'>\" +\r\n            \"#{item}\" +\r\n            \"</foreach>\"+\r\n            \"</script>\")\r\n    List<Tag> getByIdsTags(@Param(\"tagids\") List<Integer> tagids);\r\n```\r\n使用这种方式可以实现遍历出每一个tagid\r\ncollection:代表的就是集合这里直接就是@Param中的值\r\nitem：代表值\r\nindex：下标\r\nopen=\'(\'：意思就是以（开始\r\nseparator=\',\'：元素之间以，号隔开\r\nclose=\')\'：以close结束\r\n## 第二部分\r\n得到每个标签之后就需要把标签和博客之间建立联系\r\n因为我的博客表中没有标签id的字段，所以又建了一张博客标签表\r\n当我新增一条博客时在save方法使用这个注释\r\n` @Options(useGeneratedKeys=true,keyProperty=\"bid\",keyColumn=\"bid\")`\r\n就可以获得当前插入对象的id直接，对象.id就可以获取使用，为接下来的标签和博客建立关系，插入博客标签表时因为会有多条标签所以直接使用了循环多条插入语句一次插入代码如下：\r\n```java\r\n /*一个博客所对应多个标签插入*/\r\n    @Insert(\"<script>\"\r\n            + \"<foreach collection=\'ids\' item=\'item\' index=\'index\' open=\' \' separator=\';\' close=\' \'>\" +\r\n                    \"INSERT INTO blog_tags(bid,tagid) VALUES (#{bid},#{item})\" +\r\n               \"</foreach> \"+\r\n            \"</script>\")\r\n    int insertBlogTag(@Param(\"ids\") List<Integer> ids,Integer bid);\r\n```\r\n直接用空格开始空格结束分号隔开，这样就会获取三条这样的语句\r\n```sql\r\nINSERT INTO blog_tags(bid,tagid) VALUES (15,4) ;\r\nINSERT INTO blog_tags(bid,tagid) VALUES (15,5) ;\r\nINSERT INTO blog_tags(bid,tagid) VALUES (15,6)\r\n```\r\n需要注意的是要在mysql的url上加上\r\n```java\r\nurl：jdbc：mysql://localhost:3306/xxx?characterEncoding=utf-8&allowMultiQueries=true\r\n```\r\nallowMultiQueries=true\r\n否则无法生效，会报sql异常','https://i.picsum.photos/id/1014/800/450.jpg','原创',34,1,1,1,0,1,'2020-05-09 21:01:41',11,7,'使用这种方式可以实现遍历出每一个tagid\r\ncollection:代表的就是集合这里直接就是@Param中的值\r\nitem：代表值\r\nindex：下标\r\nopen=\'(\'：意思就是以（开始\r\nseparator=\',\'：元素之间以，号隔开\r\nclose=\')\'：以close结束'),(24,'卑微的测试数据','测试数据','https://picsum.photos/id/1010/800/450','翻译',12,1,1,1,0,1,'2020-05-09 20:54:42',11,7,'测试数据'),(25,'服务 Docker 化','### 一、简介\r\nDocker的出现让容器化技术得以普及，更快的部署和维护与Spring Cloud的结合，能让我们不再像以前一样为了某一个模块的增加而服务器上大动干戈，还需要考虑环境的问题。在这一篇中会讲到 SpringCloud 项目 Docker 化 。\r\n### 二、创建一个 SpringCloud 项目\r\n创建一个springcloud项目 ，包含eureka-server、service-hi、service-ribbon。\r\n##### 1. eureka-server 项目\r\npom.xml\r\n```java\r\n<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n	xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\r\n	<modelVersion>4.0.0</modelVersion>\r\n\r\n	<groupId>com.gf</groupId>\r\n	<artifactId>eureka-server</artifactId>\r\n	<version>0.0.1-SNAPSHOT</version>\r\n	<packaging>jar</packaging>\r\n\r\n	<name>eureka-server</name>\r\n	<description>Demo project for Spring Boot</description>\r\n\r\n	<parent>\r\n		<groupId>com.gf</groupId>\r\n		<artifactId>chapter02</artifactId>\r\n		<version>0.0.1-SNAPSHOT</version>\r\n	</parent>\r\n\r\n	<dependencies>\r\n		<dependency>\r\n			<groupId>org.springframework.boot</groupId>\r\n			<artifactId>spring-boot-starter-web</artifactId>\r\n		</dependency>\r\n		<dependency>\r\n			<groupId>org.springframework.cloud</groupId>\r\n			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>\r\n		</dependency>\r\n\r\n		<dependency>\r\n			<groupId>org.springframework.boot</groupId>\r\n			<artifactId>spring-boot-starter-test</artifactId>\r\n			<scope>test</scope>\r\n		</dependency>\r\n	</dependencies>\r\n\r\n	<build>\r\n		<plugins>\r\n			<plugin>\r\n				<groupId>org.springframework.boot</groupId>\r\n				<artifactId>spring-boot-maven-plugin</artifactId>\r\n				<executions>\r\n					<execution>\r\n						<goals>\r\n							<goal>repackage</goal>\r\n						</goals>\r\n					</execution>\r\n				</executions>\r\n			</plugin>\r\n		</plugins>\r\n	</build>\r\n\r\n\r\n</project>\r\n```\r\n访问 127.0.0.1:8761 注册中心正常。','https://picsum.photos/id/63/800/450.jpg','转载',2,0,1,1,0,1,'2020-05-09 20:43:00',14,7,'Docker的出现让容器化技术得以普及，更快的部署和维护与Spring Cloud的结合，能让我们不再像以前一样为了某一个模块的增加而服务器上大动干戈，还需要考虑环境的问题。在这一篇中会讲到 SpringCloud 项目 Docker 化 '),(26,'又是一条卑微的测试数据','又是一条卑微的测试数据','https://picsum.photos/id/1013/800/450','翻译',8,1,1,1,1,1,'2020-05-09 20:01:45',12,7,'Spring Security，这是一种基于 Spring AOP 和 Servlet 过滤器的安全框架。它提供全面的安全性解决方案，同时在 Web 请求级和方法调用级处理身份确认和授权'),(27,'又又又是一条测试数据诶','又又又是一条测试数据诶','https://i.picsum.photos/id/18/800/450.jpg','原创',7,1,1,1,0,1,'2020-05-08 14:41:21',15,7,'Apollo（阿波罗）是携程框架部门研发的分布式配置中心，能够集中化管理应用不同环境、不同集群的配置，配置修改后能够实时推送到应用端，并且具备规范的权限、流程治理等特'),(28,'他来了他来了测试数据走来了','他来了他来了测试数据走来了','https://picsum.photos/id/1037/800/450','翻译',1,1,1,1,1,1,'2020-05-09 12:04:41',12,7,'在之前的文章的Spring Cloud Gateway初体验中，大家已经对Spring Cloud Gateway的功能有一个初步的认识，网关作为一个系统的流量的入口，有着举足轻重的作用'),(29,'Test Docker title','Test Docker title','https://picsum.photos/id/1013/800/450','翻译',3,1,1,1,1,1,'2020-05-09 12:04:40',14,7,'Test Docker title'),(30,'尘埃里的测试数据','\r\n------------\r\n\r\n## **他来了他来了测试数据又来了**&spades; &clubs; &hearts; &diams;','https://picsum.photos/id/1009/800/450','翻译',1,0,0,0,1,1,'2020-05-08 14:39:09',15,7,'测试数据又又又又又来了'),(32,'测试的','测试的','https://picsum.photos/id/63/800/450.jpg','翻译',0,1,1,1,1,1,'2020-05-09 12:08:54',14,7,'测试的测试的');

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `cid` int(100) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(100) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `content` varchar(20) DEFAULT NULL,
  `avatar` varchar(20) DEFAULT NULL,
  `cratetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `bid` int(100) DEFAULT NULL,
  `parentcommentid` int(100) DEFAULT NULL,
  `admincomment` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`cid`,`nickname`,`email`,`content`,`avatar`,`cratetime`,`bid`,`parentcommentid`,`admincomment`) values (107,'路人张','951815578@qq.com','路人张测试评论功能','/img/user.jpg','2020-05-09 19:53:42',17,NULL,0),(108,'路人甲','951815578@qq.com','路人甲测试','/img/user.jpg','2020-05-09 19:54:03',17,NULL,0),(109,'路人甲','951815578@qq.com','路人甲测试回复路人张','/img/user.jpg','2020-05-09 19:54:22',17,107,0),(110,'Smile','ityongzhi@gmail.com','博主测试回复路人','/img/me.jpg','2020-05-09 19:55:26',17,109,1),(111,'Smile','ityongzhi@gmail.com','博主测试评论','/img/me.jpg','2020-05-09 19:55:37',17,NULL,1),(112,'Smile','ityongzhi@gmail.com','博主测试','/img/me.jpg','2020-05-09 19:55:53',17,108,1);

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `tagid` int(50) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`tagid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

insert  into `tag`(`tagid`,`tagname`) values (8,'BUG'),(9,'Mybatis'),(10,'学习'),(11,'SpringCloud'),(12,'JVM'),(14,'MYSQL'),(15,'SpringBoot'),(16,'VUE'),(17,'JAVAS');

/*Table structure for table `takenote` */

DROP TABLE IF EXISTS `takenote`;

CREATE TABLE `takenote` (
  `tid` int(100) NOT NULL AUTO_INCREMENT,
  `content` varchar(300) DEFAULT NULL,
  `cratetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uid` int(100) DEFAULT NULL,
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `takenote` */

insert  into `takenote`(`tid`,`content`,`cratetime`,`uid`) values (2,'一个BUG改了5个小时！！不过好在功能完成了','2020-04-30 00:20:58',NULL),(3,'该实现评论功能了 ~.~','2020-05-05 18:17:16',NULL),(4,'想在博客里面加Redis半天也没搞好.. ','2020-05-09 21:06:56',NULL),(5,'缓存用了Mybatis的二级缓存，看网上说比较鸡肋，不过感觉还可以','2020-05-09 20:32:02',NULL),(6,'准备开始加图片上传功能','2020-05-09 20:34:01',NULL);

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `typeid` int(10) NOT NULL AUTO_INCREMENT,
  `typename` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`typeid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`typeid`,`typename`) values (11,'Mybatis'),(12,'SpringCloud'),(14,'DocKer'),(15,'ZooKeeper'),(16,'面试'),(17,'错误日志'),(18,'算法');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `type` int(10) DEFAULT '1',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bid` int(100) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`nickname`,`username`,`password`,`email`,`type`,`uptime`,`bid`) values (1,'Smile','smile','951815578','ityongzhi@163.com',1,'2020-04-26 00:34:50',NULL),(7,'Smile','ityongzhi@163.com','123456','ityongzhi@gmail.com',1,'2020-05-08 12:19:50',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
