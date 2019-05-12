import csv
import json

csv_path = "resource/emoji_unicode.csv"
json_path = "resource/emoji.json"

json_content = {}
with open(csv_path) as csv_file:
    reader = csv.reader(csv_file)
    for row in reader:
        emoji = ''
        exec("emoji=u'"+row[0]+"'")
        #exec("emoji=u'\\u"+row[1]+"'")
        #print(emoji)
        json_content[str(reader.line_num-1)] = emoji

with open(json_path, "w") as json_file:
    json_file.write(json.dumps(json_content))