#include "Utils.h"
#include <regex>
#include <algorithm>

namespace Utils {

    bool isValidDate(const std::string& dateStr) {
        // Simple YYYY-MM-DD format validation
        std::regex datePattern("^\\d{4}-\\d{2}-\\d{2}$");
        return std::regex_match(dateStr, datePattern);
    }

    std::string trim(const std::string& str) {
        auto start = str.begin();
        while (start != str.end() && std::isspace(*start)) {
            ++start;
        }

        auto end = str.end();
        do {
            --end;
        } while (std::distance(start, end) > 0 && std::isspace(*end));

        return std::string(start, end + 1);
    }

}
