given:
input中的字母存在graph中 && 输入的字母数量大于等于2 && 字母和字母之间存在路线
when:
计算
then:
返回正确的距离

given:
input中有字母不存在graph中
when:
计算
when:
throw exception

given:
input中输入的字母数量小于2
when:
计算
when:
throw exception

given:
input中输入的有不合法的参数
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
