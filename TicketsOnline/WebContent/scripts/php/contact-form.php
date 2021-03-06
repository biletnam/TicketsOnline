<?php
$emailTo       = '<fede@pixor.it>';
$sender_email = 'contacts@framework-y.com';
$subjectPrefix = 'You received a new message';


$errors = array();
$data   = array();
$body    = '';
$email = '';
$name = '';
$domain = $_POST['domain'];

if($_SERVER['REQUEST_METHOD'] === 'POST') {
    $arr = $_POST['values'];
    $sender_email = 'contacts@' . $domain;
    $email = 'no-replay@' . $domain;

    if (strlen($_POST['email']) > 0)  $emailTo = $_POST['email'];
    if (strlen($_POST['subject_email']) > 0) $subjectPrefix = base64_encode($_POST['subject_email']);
    else $subjectPrefix = $domain . ' - You received a new message';

    foreach ($arr as $key => $value ) {
        $val =  stripslashes(trim($value[0]));
        if (!empty($val)) {
            $body .= ucfirst($key) . ': ' . $val . PHP_EOL . PHP_EOL;
            if ($key == "email"||$key == "Email"||$key == "E-mail"||$key == "e-mail") $email = $val;
            if ($key == "name"||$key == "nome"||$key == "Nome") $name = $val;
        }
    }
    $body .= "-------------------------------------------------------------------------------------------" . PHP_EOL . PHP_EOL;
    $body .= "New messagge from " . $domain;
    if ($name == '') $name = $subjectPrefix;

    if (!empty($errors)) {
        $data['success'] = false;
        $data['errors']  = $errors;
    } else {
        $headers = "From: $sender_email" . PHP_EOL;
		$headers .= "Reply-To: $email" . PHP_EOL;
		$headers .= "MIME-Version: 1.0" . PHP_EOL;
		$headers .= "Content-type: text/plain; charset=utf-8" . PHP_EOL;
		$headers .= "Content-Transfer-Encoding: quoted-printable" . PHP_EOL;

        if (mail($emailTo, $subjectPrefix, $body, $headers)) {
           $data['success'] = true;
           $data['message'] = 'Congratulations. Your message has been sent successfully.';
       } else {
           $data['success'] = false;
           $data['message'] = 'Error. Messagge not sent.';
       }
    }
    // return all our data to an AJAX call
    echo json_encode($data);
}
