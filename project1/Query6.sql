SELECT s.snum, s.name
FROM students s
JOIN minor m ON s.snum = m.snum
ORDER BY s.snum;
