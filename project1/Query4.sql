SELECT s.name
FROM students s
JOIN register r ON s.snum = r.snum
WHERE r.regtime = 'Fall2022';
