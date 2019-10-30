#include <iostream>
#include <queue>

using namespace std;

template <typename T>
vector<T> Merge (vector<T>& B, vector<T>& C);

template <typename T>
vector<T> MergeSort (vector<T>& A) {
    int size = A.size();
    if (size == 1) {
        return A;
    }
    int m = size / 2;
    vector <T> BService = {begin(A), begin(A) + m};
    for (auto elem : BService) {
        cout << elem << " ";
    }
    cout << endl;
    vector <T> CService = {begin(A) + m, end(A)};
    vector<T> B = MergeSort(BService);
    vector<T> C = MergeSort(CService);
    vector<T> A2 = Merge(B, C);
    return A2;
}

template <typename T>
vector<T> Merge (vector<T>& B, vector<T>& C) {
    vector<T> D;
    while (!B.empty() && !C.empty()) {
        T b = B.front();
        T c  = C.front();
        if (b <= c) {
            D.push_back(b);
            B.erase(begin(B));
        }
        else {
            D.push_back(c);
            C.erase(begin(C));
        }
    }
    if (C.empty()) {
        D.insert(D.end(), B.begin(), B.end());
    }
    else {
        D.insert(D.end(), C.begin(), C.end());
    }
    return D;
}

int main() {
    cout << "Enter the quantity: " << endl;
    int Q;
    cin >> Q;
    cout << "Enter the sequence: " << endl;
    vector <int> seq (Q);
    for (int i = 0; i < Q; i++) {
        cin >> seq[i];
    }
    for (auto elem : MergeSort(seq)) {
        cout << elem << " ";
    }
    return 0;
}