from Community.service.write_page_info import WritePostFlow


a,b = WritePostFlow().DoWritePost('1', 'content')
print(a,b)
