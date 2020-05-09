1.开始撸后台的第一天，下载依赖就出了问题..
**Maven一直报红线：**
**解决办法**：依赖坐标全部删除重新生成，最后还有一个包爆红，
然后去官网https://repo.maven.apache.org/maven2/找到这个包下载后导入本地仓库
重启idea之后解决.
2.application.class一定要放在最外面目录，否则访问不到contorller
3.yml配置path：server： servlet： context-path: /text