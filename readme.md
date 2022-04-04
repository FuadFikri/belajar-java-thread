# Belajar Java Thread
source : Programmer Zaman Now


### Concurrency vs Parallel
- Concurrency artinya mengerjakan beberapa pekerjaan satu persatu pada satu waktu
- Parallel artiya mengerjakan beberapa pekerjaan sekaligus pada satu waktu
- Browser akan melakukan proses concurrent ketika membuka web, browser akan melakukan http request, lalu mendownload semua file web (html, css, js) lalu merender dalam bentuk tampilan web
- Browser akan melakukan proses parallel, ketika kita membuka beberapa tab web, dan juga sambil download beberapa file, dan menonton video dari web streaming 

### Main Thread 
- Secara default, saat sebuah aplikasi Java berjalan, minimal akan ada satu thread yang berjalan
- Dalam aplikasi Java biasa, biasanya kode program kita akan berjalan di dalam thread yang bernama main


### Thread sleep
- Pada saat proses development, kada kita butuh melakukan simulasi proses yang berjalan dalam waktu tertentu


### Thread Join
- menunggu sampai sebuah thread selesai

### Thread Interrupt
- Interrupt merupakan mengirim sinyal ke thread bahwa thread tersebut harus berhenti melakukan pekerjaannya saat ini
- Untuk melakukan interrupt, kita bisa menggunakan method interrupt() pada thread
- Saat kita memanggil method interrupt(), secara otomatis Thread.interrupted() akan bernilai true
- kode program kita pada Runnable harus melakukan pengecekan interrupted, jika tidak, sinyal interrupt tidak ada gunanya


### Thread name
- Thread name secara default akan menggunakan nama Thread-{counter}
- Namun kita juga bisa mengubahnya dengan menggunakan method setName(name), dan getName() untuk mendapatkan nama thread nya

## Thread state
- status sebuah thread