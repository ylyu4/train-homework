given:
input中输入的字母数量等于2 && 字母和字母之间存在路线
when:
计算
then:
返回正确的距离

given:
input中输入的字母数量小于2
when:
计算
when:
throw exception

given:
input为null
when:
计算
when:
throw exception

given:
input中输入的字母和字母之间不存在路线
when:
计算
when:
返回路线不存在

given:
input中有多个字母 && 每一个字母和前后字母都存在路线
when:
计算
then:
返回正确距离

given:
input中有多个字母 && 其中有一组或者多组不存在路线
when:
计算
then:
返回路线不存在