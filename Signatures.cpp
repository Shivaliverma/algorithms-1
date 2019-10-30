#include <iostream>
#include <algorithm>
#include <vector>
#include <deque>

using namespace std;

/*void Signatures (map <int, int>& line) {
    int count = 1;
    deque<int> res;
    auto it = line.begin();
    int end = it -> second;
    int common_value = end;
    it++;
    while (it != line.end()) {
        if (end < it -> first) {
            res.push_back(common_value);
            end = it -> second;
            common_value = end;
            count++;
        }
        common_value = min(common_value, it -> second);
        it++;
    }
    res.push_back(common_value);
    res.push_front(count);
    cout << res[0] << endl;
    for (int i = 1; i < res.size(); i++) {
        cout << res[i] << " ";
    }
    cout << endl;
}*/

class Line {
public:
    int begin;
    int end;
};

bool isCommon (const Line& lhs, const Line& rhs) {
    return lhs.begin <= rhs.begin && lhs.end >= rhs.end;
}

void Sort (vector <Line>& m) {
    sort(m.begin(), m.end(), [] (Line lhs, Line rhs) {
        return lhs.begin < rhs.begin && lhs.end < rhs.end;
    });
}

deque <int> Signatures (vector <Line>& v) {
    Sort (v);
    int counter = 0;
    deque <int> coordinates;
    int end = v.front().end;
    for (auto iter = v.begin() + 1; iter < v.end(); iter++) {
        if (iter -> begin <= end && iter + 1 != v.end()) {
            continue;
        }
        coordinates.push_back(min (end, iter -> end));
        counter++;
        end = iter -> end;
    }
    coordinates.push_front(counter);
    return coordinates;
}

int main() {
    int n = 0;
    cin >> n;
    vector <Line> lines(n);
    for (auto& elem : lines) {
        int begin, end;
        cin >> begin >> end;
        elem = {begin, end};
    }
    deque <int> result = Signatures(lines);
    cout << result.front() << endl;
    for (size_t i = 1; i < result.size(); i++) {
        cout << result[i] << " ";
    }
    return 0;
}