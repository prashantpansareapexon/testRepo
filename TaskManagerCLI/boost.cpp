// boost.cpp
#include <boost/filesystem.hpp>
#include <nlohmann/json.hpp>
#include <iostream>
#include <fstream>

void processJsonFile(const std::string& filename) {
    boost::filesystem::path filePath(filename);

    if (boost::filesystem::exists(filePath)) {
        std::ifstream file(filePath);
        nlohmann::json j;
        file >> j;

        std::cout << "Name: " << j["name"] << std::endl;
        std::cout << "Age: " << j["age"] << std::endl;
    } else {
        std::cout << "File not found!" << std::endl;
    }
}

int main() {
    processJsonFile("data.json");
    return 0;
}