#  Dominator

# A zero-indexed array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.

# For example, consider array A such that

#  A[0] = 3    A[1] = 4    A[2] =  3
#  A[3] = 2    A[4] = 3    A[5] = -1
#  A[6] = 3    A[7] = 3
# The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.

# Write a function

# int solution(int A[], int N);
# that, given a zero-indexed array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.

# Assume that:

# N is an integer within the range [0..100,000];
# each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
# For example, given array A such that

#  A[0] = 3    A[1] = 4    A[2] =  3
#  A[3] = 2    A[4] = 3    A[5] = -1
#  A[6] = 3    A[7] = 3
# the function may return 0, 2, 4, 6 or 7, as explained above.

# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).


def solution(A):
    A_len = len(A)
    candidate = -1
    candidate_count = 0
    candidate_index = -1

    for index in xrange(A_len):
        if candidate_count == 0:
            candidate = A[index]
            candidate_index = index
            candidate_count += 1
        else:
            if A[index] == candidate:
                candidate_count += 1
            else:
                candidate_count -= 1

    if len([number for number in A if number == candidate]) <= A_len//2:
        return -1
    else:
        return candidate_index



def solution1(A):

    candidate_ele = ''
    candidate_cnt = 0
     
    for value in A:
        if candidate_ele == '':
            candidate_ele = value
            candidate_cnt = 1
        else:
            if value != candidate_ele:
                candidate_cnt -= 1
                if candidate_cnt == 0:
                    candidate_ele = ''
            else:
                candidate_cnt += 1
         
    if candidate_cnt == 0:
        return -1
         
    cnt = 0
    last_idx = 0
     
    for idx, value in enumerate(A):
        if value == candidate_ele:
            cnt += 1
            last_idx = idx
             
    if cnt > len(A)//2:
        return last_idx
         
    return -1





#  EquiLeader
#  ----------

# A non-empty zero-indexed array A consisting of N integers is given.

# The leader of this array is the value that occurs in more than half of the elements of A.

# An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.

# For example, given array A such that:

#     A[0] = 4
#     A[1] = 3
#     A[2] = 4
#     A[3] = 4
#     A[4] = 4
#     A[5] = 2
# we can find two equi leaders:

# 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
# 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
# The goal is to count the number of equi leaders.

# Write a function:

# int solution(int A[], int N);
# that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.

# For example, given:

#     A[0] = 4
#     A[1] = 3
#     A[2] = 4
#     A[3] = 4
#     A[4] = 4
#     A[5] = 2
# the function should return 2, as explained above.

# Assume that:

# N is an integer within the range [1..100,000];
# each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
# Complexity:

# expected worst-case time complexity is O(N);
# expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).



# solution-a

def solution(A):
    A_len = len(A)
    candidate = -1
    candidate_count = 0
    candidate_index = -1

    # Find out a leader candidate
    for index in xrange(A_len):
        if candidate_count == 0:
            candidate = A[index]
            candidate_index = index
            candidate_count += 1
        else:
            if A[index] == candidate:
                candidate_count += 1
            else:
                candidate_count -= 1

    # Make sure the candidate is the leader
    leader_count = len([number for number in A if number == candidate])
    if leader_count <= A_len//2:
        # The candidate is not the leader
        return 0
    else:
        leader = candidate

    equi_leaders = 0
    leader_count_so_far = 0
    for index in xrange(A_len):
        if A[index] == leader:
            leader_count_so_far += 1
        if leader_count_so_far > (index+1)//2 and
           leader_count-leader_count_so_far > (A_len-index-1)//2:
            # Both the head and tail have leaders of the same value
            # as "leader"
            equi_leaders += 1

    return equi_leaders



# solution-b
def solution1(A):

    candidate_ele = ''
    candidate_cnt = 0
 
    for value in A:
        if candidate_ele == '':
            candidate_ele = value
            candidate_cnt = 1
        else:
            if value != candidate_ele:
                candidate_cnt -= 1
                if candidate_cnt == 0:
                    candidate_ele = ''
            else:
                candidate_cnt += 1
 
    if candidate_cnt == 0:
        return 0
 
    cnt = 0
    last_idx = 0
 
    for idx, value in enumerate(A):
        if value == candidate_ele:
            cnt += 1
            last_idx = idx
 
    if cnt < len(A)//2:
        return 0
 
    equi_cnt = 0
    cnt_to_the_left = 0
    for idx, value in enumerate(A):
        if value == candidate_ele:
            cnt_to_the_left +=1
        if cnt_to_the_left > (idx + 1)//2 and \
            cnt - cnt_to_the_left > (len(A) - idx - 1) //2:
            equi_cnt += 1
 
    return equi_cnt