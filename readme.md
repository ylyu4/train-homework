given:
input中输入的字母数量等于2 && 字母和字母之间存在路线
when:
计算距离
then:
返回正确的距离

given:
input中输入的字母数量小于2
when:
计算距离
when:
throw exception

given:
input为null
when:
计算距离
when:
throw exception

given:
input中输入的字母和字母之间不存在路线
when:
计算距离
when:
返回路线不存在

given:
input中有多个字母 && 每一个字母和前后字母都存在路线
when:
计算距离
then:
返回正确距离

given:
input中有多个字母 && 其中有一组或者多组不存在路线
when:
计算距离
then:
返回路线不存在


####

given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线 && 最大stop数量
when:
计算路线数量
then:
返回正确路线数量

given:
input中输入的字母数量等于2 && 两个字母间不存在路线 && 最大stop数量
when:
计算路线数量
then:
返回0

given:
input中输入为null || 输入的数量不正确 || stop的值不为integer
when:
计算路线数量
then:
throw exception


###

given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线 && 指定stop数量
when:
计算路线数量
then:
返回正确路线数量

given:
input中输入的字母数量等于2 && 两个字母间不存在路线 && 指定stop数量
when:
计算路线数量
then:
返回0

given:
input中输入为null || 输入的数量不正确 || stop的值不为integer
when:
计算路线数量
then:
throw exception


###

given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线
when:
计算最短route的距离
then:
返回最小的路径的距离

given:
input中输入的字母数量等于2 && 两个字母间不存在路线
when:
计算最短route的距离
then:
throw exception

given:
input中输入为null || input中输入的字母数量不等于2
when:
计算最短route的距离
then:
throw exception


### 
given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线 && 一个正整数作为距离限制
when:
计算符合规则的route数量
then:
返回正确的route数量

given:
input中输入为null || input中输入的数量不等于3
when:
计算符合规则的route数量
then:
throw exception

given:
两个字母间不存在路线
when:
计算符合规则的route数量
then:
返回0

given:
一个非正整数的参数作为距离限制
when:
计算符合规则的route数量
then:
throw exception


######
given:
input中输入的字母数量等于2 && 字母和字母之间存在路线
when:
计算trip时间
then:
返回正确的时间

given:
input中有多个字母 && 每一个字母和前后字母都存在路线
when:
计算trip时间
then:
返回正确的时间

given:
input中输入的字母数量小于2
when:
计算距离
when:
throw exception

given:
input为null
when:
计算距离
when:
throw exception

given:
input中输入的字母和字母之间不存在路线
when:
计算距离
when:
返回路线不存在

given:
input中有多个字母 && 其中有一组或者多组不存在路线
when:
计算距离
then:
返回路线不存在


####

given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线 && 最大duration的值
when:
计算路线数量
then:
返回正确路线数量

given:
input中输入的字母数量等于2 && 两个字母间不存在路线 && 最大duration的值
when:
计算路线数量
then:
返回0

given:
input中输入为null || 输入的数量不正确 || 最大duration的值不为integer
when:
计算路线数量
then:
throw exception

###

given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线 && 指定duration的值
when:
计算路线数量
then:
返回正确路线数量

given:
input中输入的字母数量等于2 && 两个字母间不存在路线 && 指定duration的值
when:
计算路线数量
then:
返回0

given:
input中输入为null || 输入的数量不正确 || duration的值不为integer
when:
计算路线数量
then:
throw exception

###

given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线
when:
计算duration最短route的duration值
then:
返回duration值

given:
input中输入的字母数量等于2 && 两个字母间不存在路线
when:
计算duration最短route的duration值
then:
throw exception

given:
input中输入为null || input中输入的字母数量不等于2
when:
计算duration最短route的duration值
then:
throw exception


### 
given:
input中输入的字母数量等于2 && 两个字母间存在1到多条路线 && 一个正整数作为duration限制
when:
计算符合duration规则的route数量
then:
返回正确的route数量

given:
input中输入为null || input中输入的数量不等于3
when:
计算符合duration规则的route数量
then:
throw exception

given:
两个字母间不存在路线
when:
计算符合duration规则的route数量
then:
返回0

given:
一个非正整数的参数作为距离限制
when:
计算符合duration规则的route数量
then:
throw exception
