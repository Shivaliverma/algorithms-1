#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <iomanip>
#include <random>

using namespace std;

class Item {
public:
    double value;
    double weight;
};

void SortVector (vector <Item>& v) {
    sort(v.begin(), v.end(), [] (Item lhs, Item rhs) {
        return lhs.value / lhs.weight > rhs.value / rhs.weight;
    });
}

double Knapsack (double& W, vector <Item> items) {
    SortVector (items);
    double total_value = 0;
    double amount = 0;
    for (auto item : items) {
        if (W == 0) {
            return total_value;
        }
        amount = min(item.weight, W);
        total_value = total_value + amount * (item.value / item.weight);
        W = W - amount;
    }
    return total_value;
}

void StressTest () {
    while (true) {
        int n = rand() % 1000 + 1;
        double W = rand() % 1000000 + 1;
        vector <Item> v (n);
        for (auto& elem : v) {
            double a = rand() % 1000000 + 1;
            double b = rand() % 1000000 + 1;
            elem = {a, b};
        }
        vector <Item> v_copy = v;
        double W_copy = W;
        double a = Knapsack (W, v);
        double b = Knapsack (W_copy, v_copy);
        if (a != b) {
            cout << "Error! " << a << " " << b << endl;
            exit (1);
        }
        cout << fixed << setprecision (4) << a << " " << b << endl;
    }
}

int main() {
    //StressTest();
    int n = 0;
    double W = 0;
    cin >> n >> W;
    vector <Item> items (n);
    for (int i = 0; i < n; i++) {
        double value = 0;
        double weight = 0;
        cin >> value >> weight;
        items[i] = {value, weight};
    }
    cout << fixed << setprecision (4) << Knapsack (W, items) << endl;
    return 0;
}