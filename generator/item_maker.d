import std.file;
import std.string;

string pathLangBase;
string pathModelBase;

string rootPath = "./";

void main(string[] args) {
    // Init the args array without the exe.
    args = args[1 .. $];

    // Main start.
    if (args.length >= 1) {
        check_root(args[0]);
    }

    pathLangBase = rootPath ~ "src/main/resources/assets/farmer_misery/lang/";
    pathModelBase = rootPath ~ "src/main/resources/assets/farmer_misery/models/item/";

    find_items();
}

void check_root(string arg) {
    import std.conv : to;

    if (!startsWith(arg, "isRoot=")) {
        return;
    }

    auto argBoolean = to!bool(arg.replace("isRoot=", ""));
    if (!argBoolean) {
        rootPath = "../";
    }
}

void find_items() {
    import std.json;
    import std.container : Array;

    const JSONValue[string] json = parseJSON(readText(pathLangBase ~ "zh_cn.json"), JSONOptions
            .escapeNonAsciiChars).object();

    foreach (JSONValue node; json.keys()) {
        auto nodename = node.str();
        if (!nodename.startsWith("item.")) {
            continue;
        }

        auto dat = nodename.split(".");
        if (dat.length < 2) {
            continue;
        }

        string data = dat[2];
        import std.stdio : writeln;

        auto filename = data ~ ".json";
        auto path = pathModelBase ~ filename;
        if (exists(path)) {
            writeln("Pass: " ~ filename);
            continue;
        }

        string input = "";
        input ~= "{\n";
        input ~= "    \"parent\": \"minecraft:item/generated\",\n";
        input ~= "    \"textures\": {\n";
        input ~= "        \"layer0\": \"farmer_misery:item/" ~ data ~ "\"\n";
        input ~= "    }\n";
        input ~= "}";

        writeln("Generator: " ~ filename);
        write(path, input);
    }
}
