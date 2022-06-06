
from Community.controller.write_page_info import WritePostFlow



msg = WritePostFlow('1', 'abc').DoWritePost()
print(msg.toJson() )