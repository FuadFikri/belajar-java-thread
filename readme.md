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

### Thread state
- status sebuah thread


### Thread daemon
- Jika kita mengubah thread menjadi daemon thread, menggunakan setDaemon(true), maka secara otomatis thread tersebut menjadi daemon thread
- Daemon thread tidak akan ditunggu jika memang program Java akan berhenti


### Race Condition
- keadaan ketika sebuah data diubah secara bersamaan oleh beberapa thread sekaligus

### synchronization 
- Masalah race condition bisa diselesaikan dengan synchronization
- synchronization merupakan fitur yg memaksa kode program hanya boleh diakses dan dieksekusi oleh 1 thread saja
- hal ini menyebabkan thread lain yang akan mengakses kode tersebut harus menunggu thread yg lebih dulu mengakses.
- sehingga proses synchronization ini lebih lambat 
- namun proses synchronization lebih aman karena tidak akan terjadi race condition


#### synchronized method
- menambahkan kata kunci thread pada method

##### Intrinsic Lock
- synchronization sebenarnya menggunakan lock
  - ketika kita menggunakan synchronized method, secara otomatis java akan membuat intrinsic lock atau monitor lock
  - ketika synchronized method dipanggil oleh thread, thread akan mencoba mendapatkan intrinsic lock,
  - setelah method selesai(sukses/error) maka thread akan mengembalikan intrinsic lock
  - jika menggunakan synchronized method, intrinsic lock otomatis dihandle oleh java

#### synchronized statement
 - saat kita menggunakan synchronized method, secara otomatis selutuh method akan ter synchronization
 - jika kita ingin menggunakan synchronization pada kode tertentu, kita bisa menggunakan synchronized statement
 - jika menggunakan synchronized statement, intrinsic lock harus dihandle manual

### Deadlock
- Kondisi dimana beberapa thread saling menunggu satu sama lain.
- Biasanya terjadi karena thread tersebut melakukan lock dan menunggu lock dari thread lain
- thread a lock x dan menunggu unlock y
- thread b lock y dan menunggu unlock x
- Masalah deadlock harus diselesaikan sendiri oleh programmer yang membuat kode program nya
