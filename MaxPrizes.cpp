#include <iostream>
#include <deque>

using namespace std;

void MaxPrizes (const int& n) {
    deque <int> prizes;
    int sum = 0;
    for (int i = 1; i <= n ; i++) {
        sum += i;
        if (sum == n) {
            prizes.push_back(i);
            break;
        }
        if (sum > n) {
            sum = sum - i - (i - 1);
            prizes.pop_back();
            prizes.push_back(n - sum);
            break;
        }
        prizes.push_back(i);
    }
    cout << prizes.size() << endl;
    for (auto& elem : prizes) {
        cout << elem << " ";
    }
}

int main() {
    int n = 0;
    cin >> n;
    MaxPrizes (n);
    return 0;
}